import { Location } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Car } from 'src/app/cars/model/car';
import { CarsService } from 'src/app/cars/service/cars.service';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.scss']
})
export class CarFormComponent {
  form: FormGroup = this.formBuilder.group({
    id: '',
    year: '',
    licensePlate: '',
    model: '',
    color: '',
  });

  constructor(
    private formBuilder: FormBuilder,
    private service: CarsService,
    private snackBar: MatSnackBar,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute
  ) {
    if (this.service.isSignedIn()) {
      const car: Car = this.route.snapshot.data['car'];
      this.form = this.formBuilder.group({
        id: car.id,
        year: [car.year, [Validators.required, Validators.minLength(4), Validators.maxLength(4)]],
        licensePlate: [car.licensePlate, [Validators.required, Validators.minLength(6), Validators.maxLength(10)]],
        model: [car.model, [Validators.required, Validators.maxLength(60)]],
        color: [car.color, [Validators.required]],
      });
    } else {
      this.router.navigate(['/users/login']);
      this.snackBar.open('Para acessar esta área você deve estar logado', 'X', {
        duration: 5000
      });
    }
  }

  onSave() {
    this.service.save(this.form.value)
      .subscribe(
        result =>
        {
          this.onSuccess();
        },
        error => {
          this.onError(error);
        });
  }

  onCancel() {
    this.location.back();
  }

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }
    if (field?.hasError('minlength')) {
      const requiredLength: number = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Tamaanho mínimo precisa ser de ${requiredLength} caracteres`;
    }
    if (field?.hasError('maxlength')) {
      const requiredLength: number = field.errors ? field.errors['maxlength']['requiredLength'] : 60;
      return `Tamaanho máximo excedido de ${requiredLength} caracteres`;
    }
    if (field?.hasError('email')) {
      return 'E-mail inválido';
    }
    return 'Campo inválido';
  }

  private onSuccess() {
    this.snackBar.open('Carro salvo com sucesso!', 'X', { duration: 5000 });
    this.location.back();
  }

  private onError(error: HttpErrorResponse) {
    console.log(error);
    this.snackBar.open(error.message, 'X', { duration: 5000 });
  }
}
