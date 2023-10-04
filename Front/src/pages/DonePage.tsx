import { useLocation } from 'react-router-dom';
import MoneyDone from '../components/pocketmoney/MoneyDone';

export interface DoneData {
  title : string;
  content : string;
  ps : string;
  isSuccess ?: boolean;
}

interface RouteState {
  state: DoneData;
}

function DonePage() {
const state = (useLocation() as RouteState).state;
  return (
    <div>
      <MoneyDone
        ps={state.ps}
        title={state.title}
        content={state.content}
        isSuccess={state.isSuccess}
      />
    </div>
  );
}

export default DonePage