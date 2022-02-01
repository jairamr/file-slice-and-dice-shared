package com.minimalism.shared.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonPropertyOrder({
    "fileName", "recordName", "processingDate",
    "numberOfIterations", "totalRecordsProcessed", "totalBytesProcessed",
    "processingTime", "iterationStatistics"
})
public class IngestServiceSummary {
    private String fileName;
    private String recordName;
    private LocalDate processingDate;

    private Set<IterationStatistics> iterationsStats;
    
    public IngestServiceSummary() {
        iterationsStats = new HashSet<>();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        if(fileName == null || fileName.isEmpty() || fileName.isBlank()) {
            fileName = "no_name_available";
        }
        this.fileName = fileName;
    }

    public String getRecordName() {
        return recordName;
    }
    public void setRecordName(String recordName) {
        if(recordName == null || recordName.isEmpty() || recordName.isBlank()) {
            recordName = "no_name_available";
        }
        this.recordName = recordName;
    }

    public LocalDate getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(LocalDate processingDate) {
        this.processingDate = processingDate;
    }

    public void addStat(IterationStatistics stat) {
        this.iterationsStats.add(stat);
    }

    @JsonGetter("iterationStatistics")
    public Set<IterationStatistics> getStats() {
        return this.iterationsStats;
    }

    public void setStats(Set<IterationStatistics> stats) {
        this.iterationsStats = stats;
    }

    public IterationStatistics getStatsFor(int iterationNumber) {
        if(iterationNumber < 0) {
            return null;
        } else if(iterationNumber >= this.numberOfIterations()) {
            return null;
        } else {
            return this.iterationsStats.stream()
                    .filter(is -> is.getIterationNumber() == iterationNumber)
                    .findFirst()
                    .orElse(null);
        }
    }

    public boolean inputFileHadInvalidRecords() {
        return !this.iterationsStats.stream()
                .filter(is -> is.getInvalidRecords() > 0)
                .findAny().isEmpty();
    }

    public boolean inputFileHadMissingInformation() {
        return !this.iterationsStats.stream()
                .filter(is -> is.getMissingInformationRecords() > 0)
                .findAny().isEmpty();
    }

    public boolean iterationHadNoErrors() {
        return !(inputFileHadInvalidRecords() || inputFileHadMissingInformation());
    }

    @JsonGetter("numberOfIterations")
    public int numberOfIterations() {
        return this.iterationsStats.size();
    }

    @JsonGetter("totalBytesProcessed")
    public long totalBytesProcessed() {
        return this.iterationsStats.stream()
        .collect(Collectors.summingLong(IterationStatistics::getProcessedBytes));
    }

    @JsonGetter("totalRecordsProcessed")
    public int totalRecordsProcessed() {
        return this.iterationsStats.stream()
        .collect(Collectors.summingInt(IterationStatistics::getProcessedRecords));
    }

    @JsonGetter("processingTime")
    public long processingTime() {
        return this.iterationsStats.stream().mapToLong(IterationStatistics::getIterationDuration).max().getAsLong();
    }

    @Override
    public String toString() {
        String returnValue = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            returnValue = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
    // public JsonObject asJson() {

    //     JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    //     for(IterationStatistics is : this.iterationsStats) {
    //         arrayBuilder.add(is.asJson());
    //     }
    //     return Json.createObjectBuilder()
    //             .add("fileName", this.getFileName())
    //             .add("recordName", this.getRecordName())
    //             .add("processingDate", this.getProcessingDate().format(DateTimeFormatter.ISO_DATE))
    //             .add("totalBytesProcessed", this.totalBytesProcessed())
    //             .add("totalRecordsProcessed", this.totalRecordsProcessed())
    //             .add("processingTimeMs", this.processingTime())
    //             .add("iterationStatistics", arrayBuilder)
    //             .build();
    // }
}
