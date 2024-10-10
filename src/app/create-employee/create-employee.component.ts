import { Component, OnInit } from "@angular/core";
import { Employee } from "../employee";
import { EmployeeService } from "../employee.service";
import { Router } from "@angular/router";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-create-employee",
  templateUrl: "./create-employee.component.html",
  styleUrls: ["./create-employee.component.css"],
})
export class CreateEmployeeComponent implements OnInit {
  employee: Employee = new Employee();
  errorMessages: any = {};

  constructor(
    private employeeService: EmployeeService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  saveEmployee() {
    this.employeeService.createEmployee(this.employee).subscribe(
      (data) => {
        console.log(data);
        alert("Employee Created Successfully");
        this.goToEmployeeList();
      },
      (error: HttpErrorResponse) => {
        if (error.error && error.error.message === "ALREADY EXISTS") {
          alert("Employee already exists. Please check the employee ID or other details.");
        }
       else if (error.status === 502) {
          this.handleValidationErrors(error.error.errors);
        } else {
          console.log(error);
        }
      }
    );
  }

  goToEmployeeList() {

    this.router.navigate(["/employees"]);
  }

  onSubmit() {
    console.log(this.employee);
    this.saveEmployee();
  }

  handleValidationErrors(errors: string[]) {
    this.errorMessages = {};

    errors.forEach((error) => {
      if (error.includes("EMP-ID")) {
        this.errorMessages.empId = error;
      }
      if (error.includes("DEPT-ID")) {
        this.errorMessages.deptId = error;
      }
      if (error.includes("First Name")) {
        this.errorMessages.firstName = error;
      }
      if (error.includes("Last Name")) {
        this.errorMessages.lastName = error;
      }
      if (error.includes("Age")) {
        this.errorMessages.age = error;
      }
      if (error.includes("Salary")) {
        this.errorMessages.salary = error;
      }
      if (error.includes("Email")) {
        this.errorMessages.emailId = error;
      }
      if (error.includes("Role")) {
        this.errorMessages.role = error;
      }
    });
  }
}
