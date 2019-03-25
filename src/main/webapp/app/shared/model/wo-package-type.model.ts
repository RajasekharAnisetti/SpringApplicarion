import { IWoPackage } from 'app/shared/model/wo-package.model';
import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';

export interface IWoPackageType {
    id?: number;
    name?: string;
    woPackages?: IWoPackage[];
    woWorkOrders?: IWoWorkOrder[];
}

export class WoPackageType implements IWoPackageType {
    constructor(public id?: number, public name?: string, public woPackages?: IWoPackage[], public woWorkOrders?: IWoWorkOrder[]) {}
}
