package com.interland.training.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeePK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int empId;
    private int deptId;

    public EmployeePK() {
    }

    public EmployeePK(int empId, int deptId) {
        this.empId = empId;
        this.deptId = deptId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePK that = (EmployeePK) o;
        return empId == that.empId && deptId == that.deptId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, deptId);
    }
}
