import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL = "http://localhost:8080/demo-service/employee"; //base URL
  private getURL = "http://localhost:8080/demo-service/employee/get";
  private updateURL = "http://localhost:8080/demo-service/employee/update";
  private deleteURL = "http://localhost:8080/demo-service/employee/delete";



  constructor(private httpClient: HttpClient) { }

  getEmployeeList(): Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseURL}/getAll`);
  }

  createEmployee(employee: Employee): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/add`, employee);
  }

  getEmployeeById(empId: number, deptId: number): Observable<Employee> {
    return this.httpClient.get<Employee>(`${this.getURL}/${empId}/${deptId}`);
  }
  
  updateEmployee(empId: number, deptId: number, employee:Employee): Observable<Object> {
    return this.httpClient.put(`${this.updateURL}/${empId}/${deptId}`, employee);
  }

   deleteEmployee(empId: number, deptId: number): Observable<Employee> {
    return this.httpClient.delete<Employee>(`${this.deleteURL}/${empId}/${deptId}`);
  }

}
