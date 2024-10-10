import { Component, OnInit } from "@angular/core";
import { EmployeeService } from "../employee.service";
import { Employee } from "../employee";
import { ActivatedRoute, Router } from "@angular/router";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-update-employee",
  templateUrl: "./update-employee.component.html",
  styleUrls: ["./update-employee.component.css"],
})
export class UpdateEmployeeComponent implements OnInit {
  empId: number;
  deptId: number;
  employee: Employee = new Employee();
  errorMessages: any = {};

  constructor(
    private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.empId = this.route.snapshot.params["empId"];
    this.deptId = this.route.snapshot.params["deptId"];

    this.employeeService.getEmployeeById(this.empId, this.deptId).subscribe(
      (data) => {
        this.employee = data;
      },
      (error) => console.log(error)
    );
  }

  onSubmit(): void {
    this.employeeService
      .updateEmployee(this.empId, this.deptId, this.employee)
      .subscribe(
        (data) => {
          this.goToEmployeeList();
        },
        (error: HttpErrorResponse) => {
          if (error.status === 400) {
            console.log("Validation Error: ", error.error);
            this.handleValidationErrors(error.error.errors);
          } else if (error.status === 502) {
            this.handleValidationErrors(error.error.errors);
          } else {
            console.log(error);
          }
        }
      );
  }

  handleValidationErrors(errors: string[]) {
    this.errorMessages = {};
    errors.forEach((error) => {
      if (error.includes("empId")) {
        this.errorMessages.empId = error;
      }
      if (error.includes("deptId")) {
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

  goToEmployeeList() {
    alert("Details Updated Successfully!");
    this.router.navigate(["/employees"]);
  }
}
