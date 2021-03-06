package ru.sfedu.dblabs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Request {
    @Id
    private long id;

    private boolean isActive;
    private long clientId;
    private long serviceId;
    private String serviceType;
    private long employeeId;
    private Date dateStart;

    public Request() {}

    public Request(long id, boolean isActive, long clientId, long serviceId, String serviceType, long employeeId, Date dateStart) {
        this.id = id;
        this.isActive = isActive;
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.employeeId = employeeId;
        this.dateStart = dateStart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return getId() == request.getId() &&
                isActive() == request.isActive() &&
                getClientId() == request.getClientId() &&
                getServiceId() == request.getServiceId() &&
                getEmployeeId() == request.getEmployeeId() &&
                Objects.equals(getServiceType(), request.getServiceType()) &&
                Objects.equals(getDateStart(), request.getDateStart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getClientId(), getServiceId(), getServiceType(), getEmployeeId(), getDateStart());
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", clientId=" + clientId +
                ", serviceId=" + serviceId +
                ", serviceType='" + serviceType + '\'' +
                ", employeeId=" + employeeId +
                ", dateStart=" + dateStart +
                '}';
    }
}
