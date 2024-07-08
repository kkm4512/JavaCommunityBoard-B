package JavaCommunityBoard.DTO.Inquiry;

public enum InquiryCategory {
    Error("오류사항"),
    Fix("개선사항"),
    Good("칭찬사항"),
    Other("그 외");

    private final String category;

    InquiryCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return category;
    }
}
