import { IShippingAddress } from 'app/shared/model/shipping-address.model';

export interface ICity {
    id?: number;
    city?: string;
    provinceId?: number;
    shippingAddresses?: IShippingAddress[];
}

export class City implements ICity {
    constructor(public id?: number, public city?: string, public provinceId?: number, public shippingAddresses?: IShippingAddress[]) {}
}
