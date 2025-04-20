import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AccountService} from 'src/app/services/account.service';
import {catchError, Observable, throwError} from 'rxjs';
import {AccountDetails} from "../../../interfaces/accountDetails";
import {GlobalErrorHandler} from "../../../core/config/errors/global-error-handler";
import {ActivatedRoute} from '@angular/router';


@Component({
  selector: 'app-a-accounts',
  templateUrl: './a-index.component.html',
  styleUrls: ['./a-index.component.css']
})
export class AIndexComponent implements OnInit {
  private errorHandler = inject(GlobalErrorHandler);
  accountFormGroup!: FormGroup;
  currentPage: number = 0;
  pageSize: number = 5;
  accountObservable!: Observable<AccountDetails>
  operationFromGroup!: FormGroup;
  errorMessage!: string;
  accountIdQuery: string = "";

  constructor(private fb: FormBuilder, private accountService: AccountService, private router: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.accountIdQuery = this.router.snapshot.queryParams['accountId'];
    this.accountFormGroup = this.fb.group({
      accountId: this.accountIdQuery ? this.accountIdQuery : this.fb.control('')
    });

    if (this.accountIdQuery) {
      this.handleSearchAccount();
    }

    this.operationFromGroup = this.fb.group({
      operationType: this.fb.control('DEBIT'),
      amount: ['0', [Validators.required, Validators.minLength(3)]],
      description: this.fb.control(null),
      accountDestination: this.fb.control(null)
    })

  }

  handleSearchAccount() {
    this.accountIdQuery = this.router.snapshot.queryParams['accountId'];
    let accountId: string = this.accountFormGroup.value.accountId;
    this.accountObservable = this.accountService.getAccount(accountId, this.currentPage, this.pageSize).pipe(
      catchError(err => {
        //this.errorMessage=err.message;
        return throwError(err);
      })
    );
  }

  gotoPage(page: number) {
    this.currentPage = page;
    this.handleSearchAccount();
  }

  handleAccountOperation() {
    let accountId: string = this.accountFormGroup.value.accountId;
    let operationType = this.operationFromGroup.value.operationType;
    let amount: number = this.operationFromGroup.value.amount;
    let description: string = this.operationFromGroup.value.description;
    let accountDestination: string = this.operationFromGroup.value.accountDestination;

    if (operationType == 'DEBIT') {
      this.accountService.debit(accountId, amount, description).subscribe({
        next: (data) => {
          alert("Success Credit");
          this.operationFromGroup.reset();
          this.handleSearchAccount();
          this.errorMessage = "Success Credit";
        },
        error: (err) => {
          this.errorMessage = err;

        }
      });
    } else if (operationType == 'CREDIT') {
      this.accountService.credit(accountId, amount, description).subscribe({
        next: (data) => {
          alert("Success Debit");
          this.operationFromGroup.reset();
          this.handleSearchAccount();
        },
        error: (err) => {
          this.errorMessage = err;
        }
      });
    }
  }
}
