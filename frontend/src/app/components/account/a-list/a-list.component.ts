import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/interfaces/customer';
import { CustomerService } from 'src/app/services/customer.service';
import {AccountDetails} from "../../../interfaces/accountDetails";

@Component({
  selector: 'app-p-edit',
  templateUrl: './a-edit.component.html',
  styleUrls: ['./a-list.component.css']
})

export class AListComponent implements OnInit {
  id!: number;
  customer!: Customer;
  public currentPath = this.router.url
  public accounts!: AccountDetails[];

  constructor(
    private route: ActivatedRoute,
    private customerService: CustomerService,
    private router: Router
  ) {}

  form: Customer = {
    id: 0,
    name: "",
    email: ""
  };

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.customerService.getAccountsByCustomerId(this.id).subscribe(
        (data) => {
          this.accounts=data;
        },
        (error: any) => {
          console.log(error);
        }
      );
    });
  }

  onSubmit(): void {
    this.customerService.updateCustomer(this.form, this.id).subscribe(
      (data: Customer) => {
        this.router.navigate(["admin/customer"])
        // Handle success response
      },
      (error: any) => {
        console.log(error);
        if (error.status === 400) {
          // Handle specific error status
          console.log('Bad Request: Session expired');
        } else {
          // Handle other error statuses
          console.log('An error occurred');
        }
      }
    );
  }
}

