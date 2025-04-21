import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-aheader',
  templateUrl: './aheader.component.html',
  styleUrls: ['./aheader.component.css']
})
export class AheaderComponent {
  constructor(private router:Router){}

  logout(): void{
    this.router.navigate(['/'])
  }
}

