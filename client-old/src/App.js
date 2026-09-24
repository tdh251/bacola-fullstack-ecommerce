import "./style.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./Pages/Home";
import Header from "./Layouts/Header";

function App() {
   return (
      <BrowserRouter>
         <Header />
         <Routes>
            <Route path="/" exact={true} element={<Home />} />
         </Routes>
      </BrowserRouter>
   );
}

export default App;
