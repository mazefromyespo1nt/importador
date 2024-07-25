package com.cargabatch.importador.DTO;

public class FileDTO {

    private String fileName;
    private byte[] fileContent;
    private String fileType;

    public FileDTO(String fileName, byte[] fileContent, String fileType) {
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.fileType = fileType;
    }

    // Getters and Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
