package com.mvc.service;

import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mvc.entity.products.Group;
import com.mvc.entity.products.Level;
import com.mvc.entity.products.Product;
import com.mvc.entity.products.Size;

@Service
public class ProductServiceImpl implements ProductService {
	//模擬資料庫資訊
	{
		if (groups.size() == 0) {
			// 初始化商品分類資料
			groups.put(1, new Group(11, "A"));
			groups.put(2, new Group(21, "B"));
			groups.put(3, new Group(31, "C"));
		}
		if(sizes.size() == 0) {
			// 初始化商品尺寸資料
			sizes.put(1, new Size(1, "S"));
			sizes.put(2, new Size(2, "M"));
			sizes.put(3, new Size(3, "L"));
			sizes.put(4, new Size(4, "XL"));
			sizes.put(5, new Size(5, "XXL"));
		}
		if(levels.size() == 0) {
			levels.put(1, new Level(1, "一般"));
			levels.put(2, new Level(2, "會員"));
			levels.put(3, new Level(3, "員工"));
		}
	}

	@Override
	public List<Product> query() {
		return products;
	}

	@Override
	public Product get(String name) {
		Optional<Product> opt = products.stream().filter(p -> p.getName().equals(name)).findFirst();

		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public boolean save(Product product) {
		// 1. 根據 group.gid 找到 group 物件
		// 2. 將 group 物件存放到 product 中
		Optional<Entry<Integer, Group>> ent = groups.entrySet().stream()
				.filter(entry -> entry.getValue().getGid().equals(product.getGroup().getGid())).findAny();
		Group group = ent.get().getValue();
		product.setGroup(group);
		products.add(product);
		return true;
	}

	@Override
	public boolean update(Product product) {
		// 是否有此筆資料
		Product oProduct = get(product.getName());
		if (oProduct == null) {// 無找到該筆資料
			return false;
		}
		// 進行update
		oProduct.setGroup(product.getGroup());
		oProduct.setSize(product.getSize());
		oProduct.setLevelIds(product.getLevelIds());
		oProduct.setPrice(product.getPrice());
		oProduct.setAmount(product.getAmount());
		oProduct.setDate(product.getDate());
		return true;
	}

	@Override
	public boolean delete(String name) {
		return products.removeIf(n -> n.getName().equals(name));
	}

}
