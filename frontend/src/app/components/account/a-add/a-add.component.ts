import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Customer} from 'src/app/interfaces/customer';
import {CustomerService} from 'src/app/services/customer.service';
import {AccountDetails, AccountOperation} from "../../../interfaces/accountDetails";
import {AccountService} from "../../../services/account.service";

@Component({
  selector: 'app-p-add',
  templateUrl: './a-add.component.html',
  styleUrls: ['./a-add.component.css']
})

export class AAddComponent implements OnInit {
  public customers!: Customer[];
  public currentPage: number = 1;
  public totalPages: number = 5;
  public pageSize: number = 5;
  customer: Customer = {
    id: 0,
    name: "",
    email: ""
  };

  public form = {} as  AccountDetails;

  public currentPath = this.router.url

  constructor(
    private accountService: AccountService,
    private customerService: CustomerService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.getCustomers(this.currentPage, this.pageSize);

  }

  getCustomers(page: number = 1, size: number = 4) {
    this.customerService.getCustomers().subscribe(
      (data) => {
        this.customers = data;
        //this.totalPages=this.products.length / this.pageSize
      }
    );
  }

  onSubmit(): void {
    this.accountService.addAccount(this.form).subscribe(
      (data) => {
        this.router.navigate(["customer"])
      }
    );
  }
}
