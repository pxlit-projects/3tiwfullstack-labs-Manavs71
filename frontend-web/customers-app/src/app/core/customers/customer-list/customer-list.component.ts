import { Component } from '@angular/core';
import {FilterComponent} from "../filter/filter.component";
import {CustomerItemComponent} from "../customer-item/customer-item.component";

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [
    FilterComponent,
    CustomerItemComponent
  ],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css'
})
export class CustomerListComponent {

}
