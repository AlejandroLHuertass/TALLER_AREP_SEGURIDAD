package edu.escuelaing.arem.ASE.app.Repo_DB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseHash {

    private List<HashEntry> hashEntries;

    public MyServiceDAO() {
        this.hashEntries = new ArrayList<>();
    }

    public void addHash(Date date, String hash) {
        hashEntries.add(new HashEntry(date, hash));
    }

    public List<HashEntry> listHashes() {
        return hashEntries.subList(Math.max(0, hashEntries.size() - 10), hashEntries.size());
    }

    public void updateHash(Date date, String hash) {
        for (HashEntry entry : hashEntries) {
            if (entry.getHash().equals(hash)) {
                entry.setTimestamp(date);
                break;
            }
        }
    }

    public void deleteHash(String hash) {
        hashEntries.removeIf(entry -> entry.getHash().equals(hash));
    }

    public static class HashEntry {
        private Date timestamp;
        private String hash;

        public HashEntry(Date timestamp, String hash) {
            this.timestamp = timestamp;
            this.hash = hash;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
    }
}