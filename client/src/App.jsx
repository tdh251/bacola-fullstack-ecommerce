import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Header from "./components/Header";

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
