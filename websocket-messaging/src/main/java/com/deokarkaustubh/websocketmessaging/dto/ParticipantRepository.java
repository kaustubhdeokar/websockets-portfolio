package com.deokarkaustubh.websocketmessaging.dto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sergi Almar
 */
public class ParticipantRepository {

    private Map<String, UserLoginEvent> activeSessions = new ConcurrentHashMap<>();

    public void add(String sessionId, UserLoginEvent event) {
        activeSessions.put(sessionId, event);
    }

    public UserLoginEvent getParticipant(String sessionId) {
        return activeSessions.get(sessionId);
    }

    public void removeParticipant(String sessionId) {
        activeSessions.remove(sessionId);
    }

    public Map<String, UserLoginEvent> getActiveSessions() {
        return activeSessions;
    }

    public void setActiveSessions(Map<String, UserLoginEvent> activeSessions) {
        this.activeSessions = activeSessions;
    }
}
