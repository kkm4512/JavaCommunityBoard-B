package JavaCommunityBoard.DTO.Inquiry;

public enum CompleteInquiryCompleted {
    IN_PROCESS("처리중"),
    FULFILLED("처리완료"),
    REJECTED("처리반려");

    private final String status;

    CompleteInquiryCompleted(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}

