import { Company } from './company';

export class Coupon {
  public constructor(
    public id?: number,
    public company?: Company,
    public amount?: number,
    public price?: number,
    public title?: string,
    public description?: string,
    public category?: CategoryTypes,
    public startDate?: Date,
    public endDate?: Date,
    public image?: string
  ) {}
}

export enum CategoryTypes {
  DontUse,
  Automotive,
  Beauty,
  Food,
  Health,
  Hotels,
  Retail,
}
