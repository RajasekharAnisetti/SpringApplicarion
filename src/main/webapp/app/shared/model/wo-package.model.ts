export interface IWoPackage {
    id?: number;
    description?: string;
    length?: number;
    width?: number;
    height?: number;
    weight?: number;
    cubedWeight?: number;
    trackingNumber?: string;
    codValue?: number;
    insuranceAmount?: number;
    oid?: number;
    position?: number;
    freightClass?: string;
    type?: string;
    woPackageTypeId?: number;
    woWorkOrderId?: number;
}

export class WoPackage implements IWoPackage {
    constructor(
        public id?: number,
        public description?: string,
        public length?: number,
        public width?: number,
        public height?: number,
        public weight?: number,
        public cubedWeight?: number,
        public trackingNumber?: string,
        public codValue?: number,
        public insuranceAmount?: number,
        public oid?: number,
        public position?: number,
        public freightClass?: string,
        public type?: string,
        public woPackageTypeId?: number,
        public woWorkOrderId?: number
    ) {}
}
