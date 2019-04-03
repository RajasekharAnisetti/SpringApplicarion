import { IShippingAddress } from 'app/shared/model/shipping-address.model';
import { ICity } from 'app/shared/model/city.model';

export interface IProvince {
    id?: number;
    name?: string;
    fullName?: string;
    countryId?: number;
    shippingAddresses?: IShippingAddress[];
    cities?: ICity[];
}

export class Province implements IProvince {
    constructor(
        public id?: number,
        public name?: string,
        public fullName?: string,
        public countryId?: number,
        public shippingAddresses?: IShippingAddress[],
        public cities?: ICity[]
    ) {}
}
