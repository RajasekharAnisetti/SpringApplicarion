import { IProvince } from 'app/shared/model/province.model';
import { IShippingAddress } from 'app/shared/model/shipping-address.model';

export interface ICity {
    id?: number;
    city?: string;
    province?: IProvince;
    shippingAddresses?: IShippingAddress[];
}

export class City implements ICity {
    constructor(public id?: number, public city?: string, public province?: IProvince, public shippingAddresses?: IShippingAddress[]) {}
}
