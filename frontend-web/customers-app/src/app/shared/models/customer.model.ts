export class Customer {
  name: string;
  email: string;
  city: string;
  address: string;
  country: string;
  vat: number;
  isLoyal: boolean;
  avatar: string;
  id?: number;


  constructor(name: string, email: string, city: string, address: string, country: string, vat: number, avatar: string = 'assets/avatar.png'){
    this.name = name;
    this.email = email;
    this.city = city;
    this.address = address;
    this.country = country;
    this.vat = vat;
    this.isLoyal = false;
    this.avatar = avatar;
  }

}
