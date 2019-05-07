import { IWoTransportModes } from 'app/shared/model/wo-transport-modes.model';

export interface ITransportModes {
    id?: number;
    name?: string;
    woTransportModes?: IWoTransportModes[];
}

export class TransportModes implements ITransportModes {
    constructor(public id?: number, public name?: string, public woTransportModes?: IWoTransportModes[]) {}
}
