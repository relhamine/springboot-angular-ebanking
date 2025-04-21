import {Customer} from "./customer";

export interface AccountDetails {
  id: string;
  accountId: string;
  balance: number;
  plafond: number;
  overDraft: number;
  currentPage: number;
  totalPages: number;
  pageSize: number;
  customerDTO: Customer;
  accountOperationDTOS: AccountOperation[];
}

export interface AccountOperation {
  id: number;
  operationDate: Date;
  amount: number;
  type: string;
  description: string;
}
