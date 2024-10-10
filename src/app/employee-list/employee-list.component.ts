import { Component, OnInit, AfterViewInit, OnDestroy } from "@angular/core";
import { Employee } from "../employee";
import { EmployeeService } from "../employee.service";
import { Router } from "@angular/router";
import * as $ from "jquery";
import "datatables.net";
import "datatables.net-dt";

@Component({
  selector: "app-employee-list",
  templateUrl: "./employee-list.component.html",
  styleUrls: ["./employee-list.component.css"],
})
export class EmployeeListComponent implements OnInit, AfterViewInit, OnDestroy {
  employees: Employee[] = [];
  selectedEmployee: Employee | null = null;
  
  documentClickListener: any;

  constructor(
    private employeeService: EmployeeService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  ngAfterViewInit(): void {
    this.getEmployees();
    
    this.documentClickListener = (event: Event) => {
      if (!$(event.target).closest("#employeeTable").length) {
        this.unselectRow();
      }
    };

    $(document).on("click", this.documentClickListener);
  }

  ngOnDestroy(): void {
    $(document).off("click", this.documentClickListener);
  }

  private getEmployees() {
    this.employeeService.getEmployeeList().subscribe((data) => {
      this.employees = data;
      this.initDataTable();
    });
  }

  private initDataTable() {
    if ($.fn.dataTable.isDataTable("#employeeTable")) {
      $("#employeeTable").DataTable().clear().destroy();
    }

    const table = $("#employeeTable").DataTable({
      data: this.employees,
      columns: [
        { data: "empId" },
        { data: "deptId" },
        { data: "firstName" },
        { data: "lastName" },
        { data: "emailId" },
        { data: "age" },
        { data: "salary" },
        { data: "role" },
      ],
    });

    $("#employeeTable tbody").on("click", "tr", (event) => {
      $("#employeeTable tbody tr").removeClass("selected");
      $(event.currentTarget).addClass("selected");

      const selectedData = table.row(event.currentTarget).data();
      if (selectedData) {
        this.selectedEmployee = selectedData;
        this.enableButtons(true);
      }
    });

    $("#deleteBtn").on("click", () => this.deleteSelectedEmployee());
    $("#updateBtn").on("click", () => this.updateSelectedEmployee());
  }

  private unselectRow() {
    $("#employeeTable tbody tr").removeClass("selected");
    this.selectedEmployee = null;
    this.enableButtons(false);
  }

  private enableButtons(enabled: boolean) {
    const actionButtons = ["#deleteBtn", "#updateBtn"];
    actionButtons.forEach((buttonId) => {
      $(buttonId).prop("disabled", !enabled);
    });
  }

  updateSelectedEmployee() {
    if (this.selectedEmployee) {
      const { empId, deptId } = this.selectedEmployee;
      this.router.navigate(["update-employee", empId, deptId]);
    }
  }

  deleteSelectedEmployee() {
    if (this.selectedEmployee) {
      const { empId, deptId } = this.selectedEmployee;
      const confirmed = window.confirm(
        "Are you sure you want to delete the selected Employee?"
      );
      if (confirmed) {
        this.employeeService.deleteEmployee(empId, deptId).subscribe(() => {
          this.getEmployees();
          alert("Employee deleted successfully.");
        });
      }
    }
  }
}

