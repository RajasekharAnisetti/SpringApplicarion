import { ICountry } from 'app/shared/model/country.model';
import { IProvince } from 'app/shared/model/province.model';
import { ICity } from 'app/shared/model/city.model';

export interface IShippingAddress {
    id?: number;
    attention?: string;
    company?: string;
    address1?: string;
    address2?: string;
    phone?: string;
    email?: string;
    postalCode?: string;
    confirmDelivery?: boolean;
    instructions?: string;
    notifyRecipient?: boolean;
    res?: boolean;
    tailgate?: boolean;
    country?: ICountry;
    province?: IProvince;
    city?: ICity;
}

export class ShippingAddress implements IShippingAddress {
    constructor(
        public id?: number,
        public attention?: string,
        public company?: string,
        public address1?: string,
        public address2?: string,
        public phone?: string,
        public email?: string,
        public postalCode?: string,
        public confirmDelivery?: boolean,
        public instructions?: string,
        public notifyRecipient?: boolean,
        public res?: boolean,
        public tailgate?: boolean,
        public country?: ICountry,
        public province?: IProvince,
        public city?: ICity
    ) {
        this.confirmDelivery = this.confirmDelivery || false;
        this.notifyRecipient = this.notifyRecipient || false;
        this.res = this.res || false;
        this.tailgate = this.tailgate || false;
    }
}
