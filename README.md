# Phân tích mạng xã hội bằng đồ thị

Repository khung cho bài tập lớn CTDL&GT. Mã nguồn của từng thành viên sẽ được đưa lên sau qua branch riêng.

## Phân công

1. Thành viên 1: `model/User.java`, `model/SocialGraph.java` và test model.
2. Thành viên 2: `algorithm/BfsAlgorithms.java` và test BFS.
3. Thành viên 3: `algorithm/DfsAndPathAlgorithms.java` và test DFS/đường ngắn nhất.
4. Thành viên 4: JavaFX, trực quan đồ thị, CSV, tích hợp và tài liệu.

## Cấu trúc dự kiến

```text
src/
├── main/java/vn/edu/socialgraph/
│   ├── algorithm/
│   ├── io/
│   ├── model/
│   └── ui/
└── test/java/vn/edu/socialgraph/
    ├── algorithm/
    ├── io/
    └── model/
docs/
```

## Quy trình làm việc

- Mỗi thành viên tạo branch riêng từ `main`.
- Chỉ commit các file thuộc phần được phân công.
- Chạy `mvn test` trước khi tạo pull request.
- Review và tích hợp qua pull request, không push code trực tiếp vào `main`.
