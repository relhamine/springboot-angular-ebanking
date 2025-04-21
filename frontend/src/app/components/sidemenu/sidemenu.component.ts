import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent implements OnInit {
  constructor(private router: Router) {

  }

  logout(): void {
    //this.tokenService.logOut()
  }

  ngOnInit(): void {
    this.router.navigate(['/'])

  }
}


