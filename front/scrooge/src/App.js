import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Layout, Main, AccountBook, Community, MyPage, Join, Login } from './screens';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element = { <Layout> <Main/> </Layout> }/>
        <Route path="/accountBook" element = { <Layout> <AccountBook/> </Layout> }/>
        <Route path="/community" element = { <Layout> <Community/> </Layout> }/>
        <Route path="/myPage" element = { <Layout> <MyPage/> </Layout> }/>
        <Route path="/join" element = { <Join/> }/>
        <Route path="/login" element = { <Login/> }/>
      </Routes>
    </Router>
  );
}

export default App;