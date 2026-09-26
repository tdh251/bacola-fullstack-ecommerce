import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import BodyOverlay from "./components/common/BodyOverlay";
import Shop from "./pages/Shop";
import ProductDetails from "./pages/ProductDetails";

function App() {
   return (
      <BrowserRouter>
         {/* <BodyOverlay /> */}
         <Header />
         <main className="flex-1 min-h-screen pt-[60px] pb-[120px]">
            <div className="container">
               <Routes>
                  <Route path="/" exact={true} element={<Home />} />
                  <Route path="/shop" element={<Shop />} />
                  <Route path="/product/:slug" element={<ProductDetails />} />
               </Routes>
            </div>
         </main>
         <Footer />
      </BrowserRouter>
   );
}

export default App;
