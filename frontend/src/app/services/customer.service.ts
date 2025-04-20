import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from 'environment/environment';
import {Customer} from '../interfaces/customer';
import {AccountDetails} from "../interfaces/accountDetails";

@Injectable({
  providedIn: 'root'
})

export class CustomerService {
  url =environment.backendHost+'/customers'
  constructor(private http:HttpClient) { }

  public getCustomers():Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(this.url)
  }

  deleteCustomer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  addCustomer(customerData: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.url, customerData);
  }

  getCustomerById(id: number): Observable<Customer> {
    const url = `${this.url}/${id}`;
    return this.http.get<Customer>(url);
  }

  getAccountsByCustomerId(customerId: number): Observable<AccountDetails[]> {
    const url = `${this.url}/accounts/${customerId}`;
    return this.http.get<AccountDetails[]>(url);
  }

  updateCustomer(customer: Customer, id: number): Observable<Customer> {
    const url = `${this.url}/${id}`;
    return this.http.put<Customer>(url, customer);
  }

  getCustomerByKeyword(keyword: string): Observable<Customer[]> {
    const url = `${this.url}/search?keyword=${keyword}`;
    return this.http.get<Customer[]>(url);
  }

}
