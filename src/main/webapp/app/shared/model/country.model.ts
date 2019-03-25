import { IShippingAddress } from 'app/shared/model/shipping-address.model';
import { IProvince } from 'app/shared/model/province.model';

export interface ICountry {
    id?: number;
    name?: string;
    fullName?: string;
    isRestricted?: boolean;
    shippingAddresses?: IShippingAddress[];
    provinces?: IProvince[];
}

export class Country implements ICountry {
    constructor(
        public id?: number,
        public name?: string,
        public fullName?: string,
        public isRestricted?: boolean,
        public shippingAddresses?: IShippingAddress[],
        public provinces?: IProvince[]
    ) {
        this.isRestricted = this.isRestricted || false;
    }
}
