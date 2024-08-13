package store.badminton.BadmintonStore.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    CHUA_XAC_NHAN("Chưa xác nhận"),

    DA_XAC_NHAN("Đã xác nhận,Đang chờ vận chuyển"),

    DANG_GIAO_HANG("Đang giao hàng"),

    DA_GIAO_HANG("Đã giao hàng"),

    DA_HUY("Đã hủy");

    private final String description;
}
