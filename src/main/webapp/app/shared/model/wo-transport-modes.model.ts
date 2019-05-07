export interface IWoTransportModes {
    id?: number;
    transportModesId?: number;
    woCustomsBrokerageId?: number;
}

export class WoTransportModes implements IWoTransportModes {
    constructor(public id?: number, public transportModesId?: number, public woCustomsBrokerageId?: number) {}
}
