package com.aashi.QueueEase.service;

import com.aashi.QueueEase.entity.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class RecommendationService {

    @Autowired
    private MenuService menuService;

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Map<String, String>> getRecommendations() {
        List<MenuItem> items = menuService.getAvailableItems();

        if (items.isEmpty()) {
            return List.of();
        }

        StringBuilder menuList = new StringBuilder();
        for (MenuItem item : items) {
            menuList.append("- ").append(item.getName())
                    .append(" (").append(item.getCategory()).append(", ₹")
                    .append(item.getPrice()).append(")\n");
        }

        String prompt = "Here is a restaurant menu:\n" + menuList +
                "\nPick exactly 3 items to recommend to a customer today. " +
                "For each, give a short, appealing reason (max 10 words). " +
                "Respond ONLY in this exact format, one per line, no extra text:\n" +
                "ItemName | Reason";

        try {
            String response = callGemini(prompt);
            return parseRecommendations(response, items);
        } catch (Exception e) {
            System.err.println("Gemini recommendation failed: " + e.getMessage());
            return fallbackRecommendations(items);
        }
    }

    private String callGemini(String prompt) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", prompt)))
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        Map responseBody = response.getBody();
        List candidates = (List) responseBody.get("candidates");
        Map firstCandidate = (Map) candidates.get(0);
        Map content = (Map) firstCandidate.get("content");
        List parts = (List) content.get("parts");
        Map firstPart = (Map) parts.get(0);
        return (String) firstPart.get("text");
    }

    private List<Map<String, String>> parseRecommendations(String response, List<MenuItem> items) {
        List<Map<String, String>> result = new ArrayList<>();
        String[] lines = response.trim().split("\n");

        for (String line : lines) {
            if (line.contains("|")) {
                String[] parts = line.split("\\|", 2);
                String name = parts[0].trim();
                String reason = parts[1].trim();

                boolean exists = items.stream().anyMatch(i -> i.getName().equalsIgnoreCase(name));
                if (exists) {
                    result.add(Map.of("name", name, "reason", reason));
                }
            }
        }

        return result.isEmpty() ? fallbackRecommendations(items) : result;
    }

    private List<Map<String, String>> fallbackRecommendations(List<MenuItem> items) {
        List<Map<String, String>> result = new ArrayList<>();
        int count = Math.min(3, items.size());
        for (int i = 0; i < count; i++) {
            result.add(Map.of("name", items.get(i).getName(), "reason", "Chef's pick"));
        }
        return result;
    }
}
