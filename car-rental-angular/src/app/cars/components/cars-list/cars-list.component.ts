import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Car } from '../../model/car';

@Component({
  selector: 'app-cars-list',
  templateUrl: './cars-list.component.html',
  styleUrls: ['./cars-list.component.scss']
})
export class CarsListComponent {
  @Input() cars: Car[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() delete = new EventEmitter(false);
  readonly displayedColumns = ['year', 'licensePlate', 'model', 'color', 'actions'];

  constructor() {
    // Empty
  }

  onAdd() {
    this.add.emit(true);
  }

  onEdit(car: Car) {
    this.edit.emit(car);
  }

  onDelete(car: Car) {
    this.delete.emit(car);
  }
}
