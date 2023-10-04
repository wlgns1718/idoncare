import { useLocation } from 'react-router-dom';
import MoneyDone from '../components/pocketmoney/MoneyDone';

export interface DoneData {
  title : string;
  content : string;
  ps : string;
}

interface RouteState {
  state: DoneData;
}

function DonePage() {
const state = (useLocation() as RouteState).state;
  return (
    <div>
      <MoneyDone ps={state.ps} title={state.title} content={state.content} />
    </div>
  );
}

export default DonePage