const NewAccountSelectBox = () => {
  return (
    <select name="telecom h-[50px]" className="w-[100%] p-[10px]">
      <option value="NONE">이동통신사를 선택하세요</option>
      <option value="SKT">SKT</option>
      <option value="KT">KT</option>
      <option value="LG">LG</option>
    </select>
  );
};

export default NewAccountSelectBox;
