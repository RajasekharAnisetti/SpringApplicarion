import { ICountry } from 'app/shared/model/country.model';
import { IShippingAddress } from 'app/shared/model/shipping-address.model';
import { ICity } from 'app/shared/model/city.model';

export interface IProvince {
    id?: number;
    name?: string;
    fullName?: string;
    country?: ICountry;
    shippingAddresses?: IShippingAddress[];
    cities?: ICity[];
}

export class Province implements IProvince {
    constructor(
        public id?: number,
        public name?: string,
        public fullName?: string,
        public country?: ICountry,
        public shippingAddresses?: IShippingAddress[],
        public cities?: ICity[]
    ) {}
}
