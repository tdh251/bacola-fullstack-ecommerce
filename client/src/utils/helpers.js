// src/utils/slugify.js
export const createSlug = (str) => {
   return str
      .toLowerCase()
      .normalize("NFD")
      .replace(/[\u0300-\u036f]/g, "") // Xóa dấu tiếng Việt
      .replace(/[đĐ]/g, "d")
      .replace(/[^a-z0-9\s-]/g, "") // Xóa ký tự đặc biệt như $, &, %, #
      .trim()
      .replace(/\s+/g, "-") // Thay khoảng trắng bằng dấu gạch ngang
      .replace(/-+/g, "-");
};
