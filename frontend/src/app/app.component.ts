import {Component} from '@angular/core';
import {CustomerService} from "./services/customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'examen';

  constructor(private customerService: CustomerService,
              private router: Router) {
  }

  ngOnInit(): void {
  }
}
