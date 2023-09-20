type Step = 1 | 2 | 3 | 4;

export interface NewAccountCreate {
  onChangeStep: (data: number) => void;
  step: Step;
}
