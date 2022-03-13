import { ParameterValue } from '../parameterValue/parameterValue';
import { Product } from '../product/product';

export interface ApplicationForm {
  volume: number;
  products: Array<Product>;
  parameters: Array<ParameterValue>;
}