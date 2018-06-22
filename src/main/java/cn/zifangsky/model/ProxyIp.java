package cn.zifangsky.model;

import java.io.Serializable;

public class ProxyIp implements Serializable{
	private static final long serialVersionUID = -3699072211264713025L;

    public ProxyIp() {
    }

    public ProxyIp(Long id, String ip, Integer port, String type, String addr, Boolean used, String other) {
        this.id = id;
        this.ip = ip;
        this.port = port;
        this.type = type;
        this.addr = addr;
        this.used = used;
        this.other = other;
    }

    private Long id;

    private String ip;

    private Integer port;

    private String type;

    private String addr;

    private Boolean used;

    private String other;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    @Override
    public String toString() {
        return "ProxyIp{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", type='" + type + '\'' +
                ", addr='" + addr + '\'' +
                ", used=" + used +
                ", other='" + other + '\'' +
                '}';
    }
}