import {
   ArchiveBoxIcon, // Đại diện cho sản phẩm tươi mới đóng gói
   TruckIcon, // Đại diện cho giao hàng miễn phí
   ReceiptPercentIcon, // Đại diện cho mã giảm giá / khuyến mãi Mega
   CurrencyDollarIcon, // Đại diện cho giá tốt nhất
} from "@heroicons/react/24/outline";

export const FOOTER_FEATURES = [
   {
      id: 1,
      title: "Sản phẩm tươi, ngon mỗi ngày",
      icon: <ArchiveBoxIcon className="w-[26px] text-[#202435]" />,
   },
   {
      id: 2,
      title: "Miễn phí ship đơn hàng >300k",
      icon: <TruckIcon className="w-[26px] text-[#202435]" />,
   },
   {
      id: 3,
      title: "Nhiều những ưu đãi hấp dẫn",
      icon: <ReceiptPercentIcon className="w-[26px] text-[#202435]" />,
   },
   {
      id: 4,
      title: "Giá tốt nhất trên thị trường",
      icon: <CurrencyDollarIcon className="w-[26px] text-[#202435]" />,
   },
];

export const CATEGORIES_DATA = [
   {
      title: "TRÁI CÂY & RAU CỦ",
      items: [
         "Rau củ tươi",
         "Rau thơm & Gia vị",
         "Trái cây tươi",
         "Rau củ sơ chế & Giá đỗ",
         "Trái cây & Rau củ nhập khẩu",
         "Thực phẩm đóng gói",
         "Khay tiệc hỗn hợp",
      ],
   },
   {
      title: "BỮA SÁNG & SẢN PHẨM TỪ SỮA",
      items: [
         "Sữa tươi & Sữa có vị",
         "Bơ & Bơ thực vật",
         "Phô mai",
         "Trứng & Sản phẩm thay thế trứng",
         "Mật ong",
         "Mứt trái cây (Marmalade)",
         "Kem chua & Sốt chấm",
         "Sữa chua",
      ],
   },
   {
      title: "THỊT & HẢI SẢN",
      items: [
         "Xúc xích ăn sáng",
         "Xúc xích tiệc",
         "Thịt bò",
         "Thịt gà",
         "Thịt nguội cắt lát",
         "Tôm",
         "Cá phi lê tự nhiên",
         "Cua ghẹ & Thủy hải sản vỏ cứng",
         "Cá phi lê nuôi",
      ],
   },
   {
      title: "ĐỒ UỐNG",
      items: [
         "Nước khoáng & Nước tinh khiết",
         "Nước có ga",
         "Nước ngọt (Soda)",
         "Cà phê",
         "Sữa & Sữa hạt dinh dưỡng",
         "Trà & Trà lên men (Kombucha)",
         "Nước ép hộp & Túi",
         "Bia thủ công (Craft Beer)",
         "Rượu vang",
      ],
   },
];
