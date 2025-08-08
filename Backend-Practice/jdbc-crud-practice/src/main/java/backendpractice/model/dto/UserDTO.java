package backendpractice.model.dto;

public class UserDTO {

    private int code;
    private String name;
    private String phone;
    private String address;
    private String distinction;

    public UserDTO(){}

    public UserDTO(int code, String name, String phone, String address, String distinction) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.distinction = distinction;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistinction() {
        return distinction;
    }

    public void setDistinction(String distinction) {
        this.distinction = distinction;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", distinction='" + distinction + '\'' +
                '}';
    }
}
