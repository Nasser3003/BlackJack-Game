import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  url = 'http://localhost:3000';

  constructor (
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  registerForm: FormGroup = this.formBuilder.group({
    email: ['', Validators.required],
    password: ['', Validators.required],
    money: ['', Validators.required]
  });

  register(): void {
    if (this.registerForm.valid) {
      this.httpClient.post(`${this.url}/register`, this.registerForm.value).subscribe(() => {});
      this.router.navigate(["/login"]);
    }
  }
  

}
