import { Moment } from 'moment';
import { IWoPackage } from 'app/shared/model/wo-package.model';

export interface IWoWorkOrder {
    id?: number;
    customsBrokerName?: string;
    customsContactName?: string;
    customsCurrency?: string;
    customsPhoneNumber?: string;
    customsValue?: number;
    fromAirportCode?: string;
    fromLocationType?: number;
    jobContentDescr?: string;
    shipQuantity?: number;
    jobOriginalCost?: number;
    quotedAmount?: string;
    assignTo?: string;
    toCompany?: string;
    toLocationType?: number;
    dateCreated?: Moment;
    jobNumber?: string;
    jobCustomer?: string;
    serviceType?: number;
    jobSales?: string;
    woRequestType?: number;
    woPackages?: IWoPackage[];
    shipToId?: number;
    shipFromId?: number;
    woPackageTypeId?: number;
}

export class WoWorkOrder implements IWoWorkOrder {
    constructor(
        public id?: number,
        public customsBrokerName?: string,
        public customsContactName?: string,
        public customsCurrency?: string,
        public customsPhoneNumber?: string,
        public customsValue?: number,
        public fromAirportCode?: string,
        public fromLocationType?: number,
        public jobContentDescr?: string,
        public shipQuantity?: number,
        public jobOriginalCost?: number,
        public quotedAmount?: string,
        public assignTo?: string,
        public toCompany?: string,
        public toLocationType?: number,
        public dateCreated?: Moment,
        public jobNumber?: string,
        public jobCustomer?: string,
        public serviceType?: number,
        public jobSales?: string,
        public woRequestType?: number,
        public woPackages?: IWoPackage[],
        public shipToId?: number,
        public shipFromId?: number,
        public woPackageTypeId?: number
    ) {}
}
