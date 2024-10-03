import { Component } from '@angular/core';
import {FilterComponent} from "../filter/filter.component";
import {CustomerItemComponent} from "../customer-item/customer-item.component";
import {Customer} from "../../../shared/models/customer.model";

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
  customers!: Customer[];

  ngOnInit(): void {
    this.customers = [
      new Customer('Dries Swinnen', 'dries@pxl.be', 'Pelt', 'mystreet', 'Belgium', 21),
      new Customer('John Doe', 'john@doe.be', 'New York', '5th Avenue', 'USA', 6),
      new Customer('Jane Doe', 'jane@doe.be', 'Los Angeles', 'Sunset Boulevard', 'USA', 6),
    ];

    this.customers[1].isLoyal = true;
  }
}
